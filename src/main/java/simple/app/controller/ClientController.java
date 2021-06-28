package simple.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import simple.app.misc.ClientNonConfidential;
import simple.app.model.Client;
import simple.app.service.ClientService;
import simple.app.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/clients")
    @ResponseBody
    public List<ClientNonConfidential> getAllClients(@RequestParam String userLogin, @RequestParam String accessCode) {
        if(employeeService.validateAccess(userLogin, accessCode)) {
            return clientService.getAllClientsNonConfidential();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/clients/{clientId}")
    @ResponseBody
    public ClientNonConfidential getClientById(@PathVariable Long clientId, @RequestParam String userLogin,
                                               @RequestParam String accessCode) {

        if(employeeService.validateAccess(userLogin, accessCode)) {
            Optional<ClientNonConfidential> client = clientService.getClientByIdNonConfidential(clientId);
            if(client.isPresent()) {
                return client.get();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    @ResponseBody
    public void addClient(@RequestBody Client newClient) {
       if(clientService.getClientByLogin(newClient.getClientLogin()) != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login already in use");
       }

       clientService.AddClient(newClient);
    }
}
