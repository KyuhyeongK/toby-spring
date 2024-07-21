package com.troy.toby

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootTest
class TobyApplicationTests {

//    @Autowired
//    private lateinit var context: AnnotationConfigApplicationContext

    @Test
    fun contextLoads() {
        val context = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val dao = context.getBean("userDao", UserDao::class.java)
        dao.add(User("id", "troy", "troy"))
        val user = dao.get("id")
        assertThat(user.id).isEqualTo("id")
    }

    @Test
    fun `getBean 동일성 테스트`() {
        val context = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val dao1 = context.getBean("userDao", UserDao::class.java)
        val dao2 = context.getBean("userDao", UserDao::class.java)
        assertThat(dao1).isEqualTo(dao2)
    }
}
