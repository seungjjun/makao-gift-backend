package kr.megaptera.makaogift.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
  private final JdbcTemplate jdbcTemplate;

  public BackdoorController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping("setup-database")
  public String setupDatabase() {

    jdbcTemplate.execute("DELETE FROM PERSON");
    jdbcTemplate.execute("DELETE FROM PRODUCT");

    jdbcTemplate.execute("INSERT INTO PERSON(id, amount, name, user_ID)" +
        " VALUES(1, '50000', '노승준', 'jel1y')");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(1, '킹왕짱젤리', '젤리세트', '대왕젤리2개포함', '10000')");

    return "OK";
  }
}
