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

  @GetMapping("setting-product")
  public String settingProduct() {
    jdbcTemplate.execute("DELETE FROM PRODUCT");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(1, '킹왕짱젤리', '젤리세트', '대왕젤리2개포함', '10000')");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(2, '롯데', '감자칩', '어제 캔 감자로 만든', '3000')");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(3, '빙그레', '요맘때', '수억마리의 유산균이 싱싱하게 살아있는', '1000')");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(4, '씨맥스', '비타민MAX', '한달치 비타민이 들어가있는', '10000')");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(5, 'C&C', '내츄럴 티슈(50 WIPES)', '10년 묵은때도 한번에 닦을 수 있는', '5000')");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(6, 'coke', '코카콜라', '설탕은 1도 안들었지만 매우 달달한', '2500')");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(7, '롯데', '죠스바', '진짜 죠스를 먹는듯한 느낌을 가진', '1000')");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(8, '애쁠', '밀북', '한번쓰면 맥북은 생각도 나지 않는 최고의 가성비', '50000')");

    jdbcTemplate.execute("INSERT INTO PRODUCT" +
        "(id, manufacturer, name, option, price)" +
        " VALUES(9, '샘송', '네뷸라워치', '시계로 할 수 있는건 다 있다. 상상을 뛰어넘는 워치', '30000')");

    return "Ok";
  }

  @GetMapping("delete-product")
  public String deleteProduct() {
    jdbcTemplate.execute("DELETE FROM PRODUCT");

    return "OK";
  }
}
