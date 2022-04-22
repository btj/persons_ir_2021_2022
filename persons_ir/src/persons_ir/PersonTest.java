package persons_ir;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class PersonTest {

	@Test
	void test() {
		Person albert = new Person();
		assertEquals(null, albert.getFather());
		assertEquals(Set.of(), albert.getChildren());
		
		Person filip = new Person();
		filip.setFather(albert);
		assertEquals(albert, filip.getFather());
		assertEquals(Set.of(filip), albert.getChildren());
		
		Person laurent = new Person();
		laurent.setFather(albert);
		assertEquals(Set.of(filip, laurent), albert.getChildren());
		
		laurent.unregisterFather();
		assertEquals(null, laurent.getFather());
		assertEquals(Set.of(filip), albert.getChildren());
	}

}
