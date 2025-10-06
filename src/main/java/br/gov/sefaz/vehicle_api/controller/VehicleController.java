package br.gov.sefaz.vehicle_api.controller;

import br.gov.sefaz.vehicle_api.model.Vehicle;
import br.gov.sefaz.vehicle_api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    // Busca por query params (ex: ?renavam=..., ?plate=..., ?chassi=...)
    @GetMapping
    public ResponseEntity<?> getVehicles(
            @RequestParam(required = false) String plate,
            @RequestParam(required = false) String renavam,
            @RequestParam(required = false) String chassi
    ) {
        long providedFilters = java.util.stream.Stream.of(plate, renavam, chassi)
                .filter(s -> s != null && !s.isBlank())
                .count();

        if (providedFilters != 1) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Exatamente um filtro (plate, renavam ou chassi) deve ser fornecido."
            ));
        }

        List<Vehicle> vehicles = vehicleService.findVehicleByFilter(plate, renavam, chassi);

        if (vehicles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Map.of("vehicles", vehicles));
    }

    // Busca direta por RENAVAM
    @GetMapping("/renavam/{renavam}")
    public ResponseEntity<?> getByRenavam(@PathVariable String renavam) {
        List<Vehicle> vehicles = vehicleService.findVehicleByFilter(null, renavam, null);
        if (vehicles.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("vehicles", vehicles));
    }

    // Busca direta por PLACA
    @GetMapping("/plate/{plate}")
    public ResponseEntity<?> getByPlate(@PathVariable String plate) {
        List<Vehicle> vehicles = vehicleService.findVehicleByFilter(plate, null, null);
        if (vehicles.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("vehicles", vehicles));
    }

    // Busca direta por CHASSI
    @GetMapping("/chassi/{chassi}")
    public ResponseEntity<?> getByChassi(@PathVariable String chassi) {
        List<Vehicle> vehicles = vehicleService.findVehicleByFilter(null, null, chassi);
        if (vehicles.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("vehicles", vehicles));
    }
}
