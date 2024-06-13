package com.yedy.chat_app.exception;


import com.yedy.chat_app.entity.BaseEntity;
import com.yedy.chat_app.enums.ErrorMessages;

public abstract class Assert {
    public static void notNull(Object object, ErrorMessages message) {
        if (object != null)
            throw new YedyException(message.getMessage());
    }

    public static void isNull(Object object, ErrorMessages message) {
        if (object == null)
            throw new YedyException(message.getMessage());
    }

    /*public static void isIdNull(BaseEntity entity, ErrorMessages message) {
        if (entity == null || entity.getId() == null)
            throw new YedyException(message.getMessage());
    }*/

    /*public static void isIdNull(BaseEntity entity) {
        isIdNull(entity, ErrorMessages.ID_NULL);
    }*/

    public static void isTrue(boolean expression, ErrorMessages message) {
        if (expression)
            throw new YedyException(message.getMessage());
    }

    public static void isFalse(boolean expression, ErrorMessages message) {
        if (!expression)
            throw new YedyException(message.getMessage());
    }

    public static void isEmpty(String text, ErrorMessages message) {
        if (text == null || text.isEmpty())
            throw new YedyException(message.getMessage());
    }
}