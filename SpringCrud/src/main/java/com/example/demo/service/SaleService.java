package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Sale;
import com.example.demo.model.User;
import com.example.demo.repository.SaleRepository;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, UserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
    }

    // Método para crear una nueva venta
    public Sale createSale(Sale sale) {
        // Obtener el vendedor y el cliente por sus IDs
        Optional<User> optionalSeller = userRepository.findById(sale.getSeller().getId());
        Optional<User> optionalClient = userRepository.findById(sale.getClient().getId());

        if (optionalSeller.isPresent() && optionalClient.isPresent()) {
            // Asignar el nombre y el perfil del vendedor y el cliente a la venta
            User seller = optionalSeller.get();
            sale.getSeller().setName(seller.getName());
            sale.getSeller().setProfile(seller.getProfile());

            User client = optionalClient.get();
            sale.getClient().setName(client.getName());
            sale.getClient().setProfile(client.getProfile());

            // Guardar la venta en la base de datos
            return saleRepository.save(sale);
        } else {
            throw new RuntimeException("Vendedor o cliente no encontrado");
        }
    }

    // Método para obtener todas las ventas
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // Método para obtener una venta por ID
    public Sale getSaleById(Long saleId) {
        Optional<Sale> optionalSale = saleRepository.findById(saleId);
        return optionalSale.orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    // Método para actualizar una venta por ID
    public void updateSale(Long saleId, Sale updatedSale) {
        Sale existingSale = getSaleById(saleId);
        existingSale.setValue(updatedSale.getValue());
        existingSale.setDetail(updatedSale.getDetail());
        existingSale.setSeller(updatedSale.getSeller());
        existingSale.setClient(updatedSale.getClient());
        saleRepository.save(existingSale);
    }

    // Método para eliminar una venta por ID
    public void deleteSale(Long saleId) {
        saleRepository.deleteById(saleId);
    }
}



