package noticias.Controllers;

import noticias.Models.Cliente;
import noticias.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/mostrar")
    public List<Cliente> mostrarClientes() {
        return clienteService.mostrarClientes();
    }

    @PostMapping("/insertar")
    public void insertarCliente(@RequestParam String nombres, @RequestParam String apellidos, @RequestParam Integer edad, @RequestParam String fechaNacimiento,
                                @RequestParam String token, @RequestParam String correo, @RequestParam String password) {
        clienteService.insertarCliente(nombres, apellidos, edad, fechaNacimiento, token, correo, password);
    }

    @PostMapping("/verificar")
    public boolean verificarUsuario(@RequestParam String correo, @RequestParam String password) {
        return clienteService.verificarUsuario(correo, password);
    }
}
