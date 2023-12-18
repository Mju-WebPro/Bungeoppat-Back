package server.api.webpro.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.api.webpro.store.dto.StoreRequest;
import server.api.webpro.store.entity.Store;
import server.api.webpro.store.repository.StoreRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public List<StoreRequest> getAllStores() {
        return storeRepository.findAllStore();
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public Store saveOrUpdateStore(Store store) {
        return storeRepository.save(store);
    }

    public void deleteStoreById(Long id) {
        storeRepository.deleteById(id);
    }

    public List<StoreRequest> getNearbyAllStores(double latitude,double longitude) {
        return storeRepository.findNearbyStore(latitude,longitude);
    }
}
