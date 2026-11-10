package  com.eventpro.service;
import com.eventpro.dto.request.ClientRequest;
import com.eventpro.dto.response.ClientResponse;
import com.eventpro.entity.Client;
import com.eventpro.exception.ClientNotFoundException;
import com.eventpro.mapper.ClientMapper;
import com.eventpro.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientResponse creerClient(ClientRequest request) {

        Client client = clientMapper.toEntity(request);

        Client clientEnregistre = clientRepository.save(client);

        return clientMapper.toResponse(clientEnregistre);
    }

    @Transactional(readOnly = true)
    public ClientResponse recupererClient(UUID id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ClientNotFoundException(
                                "Client introuvable : " + id
                        ));

        return clientMapper.toResponse(client);
    }
}
