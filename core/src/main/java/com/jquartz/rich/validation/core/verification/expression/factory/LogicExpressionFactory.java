package com.jquartz.rich.validation.core.verification.expression.factory;

import com.jquartz.rich.validation.core.annotations.Validate;
import com.jquartz.rich.validation.core.verification.expression.LogicExpression;
import com.jquartz.rich.validation.core.verification.expression.factory.handler.FieldHandler;
import com.jquartz.rich.validation.core.verification.expression.factory.handler.Handler;
import com.jquartz.rich.validation.core.verification.expression.factory.handler.LiteralHandler;
import com.jquartz.rich.validation.core.verification.expression.value.Value;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public class LogicExpressionFactory {

    private final List<Handler> handlers = Arrays.asList(new FieldHandler(), new LiteralHandler());

    public LogicExpression<?> create(Field field, Validate validate) {


        for (Handler handler : handlers) {
            String target = validate.thanField();
            if (handler.canHandle(validate)) {
//                return new LogicExpression<>
                Value value = handler.doHandle(field, validate);
            }
        }

        return null;
    }


}
