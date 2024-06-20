package boosterschool.realestatesearchservice.exceptions.validator;

import boosterschool.realestatesearchservice.exceptions.impl.CustomValidatorNotBlankImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomValidatorNotBlankImpl.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidationNotBlank {
    String message() default "PropertyType cannot be empty or contain only spaces";

    /**
     * groups(): Этот метод позволяет указать группы,
     * к которым относится данное ограничение или валидация.
     * Группы могут быть использованы для определения различных
     * сценариев валидации и применения различных наборов ограничений в зависимости от сценария.
     * */
    Class<?>[] groups() default {};

    /**
     * payload(): Этот метод позволяет указать настраиваемые данные,
     * которые будут переданы в процессе валидации. Например, можно
     * передать дополнительную информацию о сообщении об ошибке или
     * другие параметры, необходимые для корректной валидации.
     * */
    Class<? extends Payload>[] payload() default {};
}
