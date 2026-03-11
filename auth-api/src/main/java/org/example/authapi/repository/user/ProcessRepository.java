package org.example.authapi.repository.user;

import org.example.authapi.model.ProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProcessRepository extends JpaRepository<ProcessingLog, UUID> {
}
