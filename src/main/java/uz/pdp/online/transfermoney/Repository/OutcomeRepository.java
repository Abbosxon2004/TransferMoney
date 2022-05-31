package uz.pdp.online.transfermoney.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.transfermoney.Entity.Outcome;

import java.util.List;

public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {
    List<Outcome> findAllByFromCardId(Integer fromCardId);
}
