package noticias;

import noticias.Models.Cliente;
import noticias.Services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMostrarClientes() {
        // Crear datos simulados para la prueba
        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNombres("Juan");
        cliente1.setApellidos("Perez");
        cliente1.setEdad(30);
        cliente1.setFechaNacimiento("1994-05-10");
        cliente1.setToken("token123");
        cliente1.setCorreo("juan@example.com");
        cliente1.setPassword("password");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setNombres("Maria");
        cliente2.setApellidos("Lopez");
        cliente2.setEdad(25);
        cliente2.setFechaNacimiento("1999-02-15");
        cliente2.setToken("token456");
        cliente2.setCorreo("maria@example.com");
        cliente2.setPassword("password");


        when(jdbcTemplate.query(eq("EXEC MostrarClientes"), any(RowMapper.class)))
                .thenAnswer(invocation -> {
                    RowMapper<?> rowMapper = invocation.getArgument(1); // Obtener el RowMapper
                    return Arrays.asList(cliente1, cliente2); // Simular la lista de clientes
                });

        // Llamar al método a probar
        List<Cliente> clientes = clienteService.mostrarClientes();

        // Verificar que el resultado sea el esperado
        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("Juan", clientes.get(0).getNombres());
        assertEquals("Maria", clientes.get(1).getNombres());

        // Verificar que el JdbcTemplate fue llamado correctamente
        verify(jdbcTemplate, times(1)).query(eq("EXEC MostrarClientes"), any(RowMapper.class));
    }

    @Test
    public void testInsertarCliente() {
        // Llamar al método insertarCliente y verificar que jdbcTemplate.update sea invocado correctamente
        clienteService.insertarCliente("Carlos", "Garcia", 28, "1996-08-22", "token789", "carlos@example.com", "password");

        // Verificar que el JdbcTemplate fue llamado una vez con el SQL y parámetros correctos
        verify(jdbcTemplate, times(1)).update(eq("EXEC InsertarCliente @Nombres = ?, @Apellidos = ?, @Edad = ?, @FechaNacimiento = ?, @Token = ?, @Correo = ?, @Password = ?"),
                eq("Carlos"), eq("Garcia"), eq(28), eq("1996-08-22"), eq("token789"), eq("carlos@example.com"), eq("password"));
    }

    @Test
    public void testVerificarUsuario() {
        // Simular el comportamiento del JdbcTemplate para la consulta
        when(jdbcTemplate.queryForObject(eq("EXEC VerificarUsuario @Correo = ?, @Password = ?"), eq(Integer.class), eq("juan@example.com"), eq("password")))
                .thenReturn(1); // Supongamos que el usuario existe

        // Llamar al método verificarUsuario
        boolean result = clienteService.verificarUsuario("juan@example.com", "password");

        // Verificar que el resultado sea verdadero (usuario encontrado)
        assertTrue(result);

        // Verificar que el JdbcTemplate fue llamado correctamente
        verify(jdbcTemplate, times(1)).queryForObject(eq("EXEC VerificarUsuario @Correo = ?, @Password = ?"), eq(Integer.class), eq("juan@example.com"), eq("password"));
    }

    @Test
    public void testVerificarUsuarioNoExistente() {
        // Simular el comportamiento del JdbcTemplate para la consulta con resultado nulo
        when(jdbcTemplate.queryForObject(eq("EXEC VerificarUsuario @Correo = ?, @Password = ?"), eq(Integer.class), eq("juan@example.com"), eq("wrongpassword")))
                .thenReturn(null); // Supongamos que el usuario no existe

        // Llamar al método verificarUsuario
        boolean result = clienteService.verificarUsuario("juan@example.com", "wrongpassword");

        // Verificar que el resultado sea falso (usuario no encontrado)
        assertFalse(result);

        // Verificar que el JdbcTemplate fue llamado correctamente
        verify(jdbcTemplate, times(1)).queryForObject(eq("EXEC VerificarUsuario @Correo = ?, @Password = ?"), eq(Integer.class), eq("juan@example.com"), eq("wrongpassword"));
    }

}
