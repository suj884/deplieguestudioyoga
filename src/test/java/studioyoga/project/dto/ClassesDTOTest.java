package studioyoga.project.dto;

import org.junit.jupiter.api.Test;
import studioyoga.project.model.Classes;

import static org.assertj.core.api.Assertions.assertThat;

class ClassesDTOTest {

    @Test
    void testNoArgsConstructorAndSettersAndGetters() {
        Classes yogaClass = new Classes();
        yogaClass.setId(1);
        yogaClass.setTitle("Yoga Básico");

        ClassesDTO dto = new ClassesDTO();
        dto.setClasses(yogaClass);
        dto.setSpotsLeft(5);

        assertThat(dto.getClasses()).isEqualTo(yogaClass);
        assertThat(dto.getSpotsLeft()).isEqualTo(5);
    }

    @Test
    void testAllArgsConstructor() {
        Classes yogaClass = new Classes();
        yogaClass.setId(2);
        ClassesDTO dto = new ClassesDTO(yogaClass, 10);

        assertThat(dto.getClasses()).isEqualTo(yogaClass);
        assertThat(dto.getSpotsLeft()).isEqualTo(10);
    }

    @Test
    void testEqualsAndHashCode() {
        Classes yogaClass = new Classes();
        yogaClass.setId(3);
        ClassesDTO dto1 = new ClassesDTO(yogaClass, 7);
        ClassesDTO dto2 = new ClassesDTO(yogaClass, 7);

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    void testToString() {
        Classes yogaClass = new Classes();
        yogaClass.setId(4);
        ClassesDTO dto = new ClassesDTO(yogaClass, 2);
        String str = dto.toString();
        assertThat(str).contains("4").contains("2");
    }
}