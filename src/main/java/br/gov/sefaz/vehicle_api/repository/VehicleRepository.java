package br.gov.sefaz.vehicle_api.repository;

import br.gov.sefaz.vehicle_api.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


    Optional<Vehicle> findByPlate(String plate);

    Optional<Vehicle> findByRenavam(String renavam);

    Optional<Vehicle> findByChassi(String chassi);
}