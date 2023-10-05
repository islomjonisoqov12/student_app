package com.student.student_app.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    // Администратор системный рол. Все - Полные права (Супер пользователь)
    public static final String ADMIN = "ROLE_ADMIN";

    // Пользователь - обычный*/
    public static final String TEACHER = "ROLE_TEACHER";

    // Модератор - системный рол с ограниченными возможностями
    public static final String MODERATOR = "ROLE_MODERATOR";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
