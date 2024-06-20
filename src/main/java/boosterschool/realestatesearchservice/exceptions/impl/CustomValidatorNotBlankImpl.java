package boosterschool.realestatesearchservice.exceptions.impl;

import boosterschool.realestatesearchservice.exceptions.validator.CustomValidationNotBlank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidatorNotBlankImpl implements ConstraintValidator<CustomValidationNotBlank, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        /**
         * Разрешаем null значения, но не пустые строки или строки, содержащие только пробелы
         */
        return value == null || !value.trim().isEmpty();
    }
}
