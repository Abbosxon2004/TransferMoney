package uz.pdp.online.transfermoney.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.transfermoney.Entity.Card;

public interface CardRepository extends JpaRepository<Card,Integer> {

}
