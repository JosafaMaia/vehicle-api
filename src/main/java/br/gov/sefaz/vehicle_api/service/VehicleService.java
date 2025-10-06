package br.gov.sefaz.vehicle_api.service;

import br.gov.sefaz.vehicle_api.model.Vehicle;
import br.gov.sefaz.vehicle_api.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired // Injeção de dependência: O Spring nos fornecerá uma instância do VehicleRepository
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findVehicleByFilter(String plate, String renavam, String chassi) {
        Optional<Vehicle> vehicleOptional = Optional.empty();

        if (plate != null) {
            vehicleOptional = vehicleRepository.findByPlate(plate);
        } else if (renavam != null) {
            vehicleOptional = vehicleRepository.findByRenavam(renavam);
        } else if (chassi != null) {
            vehicleOptional = vehicleRepository.findByChassi(chassi);
        }

        // Se um veículo foi encontrado, retorna uma lista com ele. Senão, retorna uma lista vazia.
        return vehicleOptional.map(List::of).orElse(Collections.emptyList());
    }
}
