package noticias.Services;

import noticias.Models.Cliente;
import noticias.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ClienteService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cliente> mostrarClientes() {
        String sql = "EXEC MostrarClientes";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("Id"));
            cliente.setNombres(rs.getString("Nombres"));
            cliente.setApellidos(rs.getString("Apellidos"));
            cliente.setEdad(rs.getInt("Edad"));
            cliente.setFechaNacimiento(rs.getString("FechaNacimiento"));
            cliente.setToken(rs.getString("Token"));
            cliente.setCorreo(rs.getString("Correo"));
            cliente.setPassword(rs.getString("Password"));
            return cliente;
        });
    }

    public void insertarCliente(String nombres, String apellidos, Integer edad, String fechaNacimiento, String token, String correo, String password) {
        String sql = "EXEC InsertarCliente @Nombres = ?, @Apellidos = ?, @Edad = ?, @FechaNacimiento = ?, @Token = ?, @Correo = ?, @Password = ?";
        jdbcTemplate.update(sql, nombres, apellidos, edad, fechaNacimiento, token, correo, password);
    }

    public boolean verificarUsuario(String correo, String password) {
        String sql = "EXEC VerificarUsuario @Correo = ?, @Password = ?";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, correo, password);
        return result != null && result > 0;
    }
}
