package server.api.webpro.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
}
