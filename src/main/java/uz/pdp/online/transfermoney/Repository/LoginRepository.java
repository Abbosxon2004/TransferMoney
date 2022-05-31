package uz.pdp.online.transfermoney.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.transfermoney.Entity.Login;

public interface LoginRepository extends JpaRepository<Login,Integer> {

}
