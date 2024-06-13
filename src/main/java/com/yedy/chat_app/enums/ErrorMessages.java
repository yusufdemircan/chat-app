package com.yedy.chat_app.enums;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    SAME_USER_NAME(1000, "Başka bir kullanıcı adı belirleyiniz."),
    USER_NAME_NULL(1001, "Kullanıcı adı boş olamaz."),
    USER_PASS_NULL(1002, "Şifre boş olamaz."),
    USER_EMAIL_NULL(1003, "E-mail boş olamaz."),
    USER_NOT_FOUND(1004, "Kullanıcı bulunamadı."),
    ROOM_SESSION_NOT_FOUND(1005, "Mekan bulunamadı."),
    WRONG_PASS(1006, "Şifre hatalı."),
    ROLE_NAME_NULL(1007, "Rol adı boş olamaz."),
    ROLE_CODE_NULL(1008, "Rol kodu boş olamaz."),
    SAME_ROLE_CODE(1009, "Aynı rol daha önce eklenmiş."),
    ID_NULL(1010, "Id boş olamaz."),
    PASS_NOT_RANGE(1011, "Şifre en az 8 en fazla 36 karakterden oluşabilir."),
    SPO_ACCESS_TOKEN_NULL(1012, "Spotify erişim anahtarı boş."),
    SPO_REFRESH_TOKEN_NULL(1013, "Spotify yenileme anahtarı boş."),
    SPO_NOT_AUTH(1014, "Spotify yetkilendirmesi yapılması gerekiyor."),
    NOT_ENOUGH_COIN(1015, "Yetersiz bakiye."),
    TABLE_NAME_IS_NULL(1016, "Tablo adı boş olamaz."),
    TOKEN_EXPIRED(1017, "Token süresi doldu."),
    TOKEN_EXPIRED_NULL(1018, "Token geçerlilik anahtarı boş."),
    REQUEST_NOT_NULL(1019, "Gönderilen istek boş olamaz."),
    USER_INFO_NOT_FOUND(1020, "Kullanıcı bilgisi bulunamadı."),
    NOT_VOTEABLE(1021, "Oylama kapalı."),
    UPLOAD_FILE_EMPTY(1022, "Dosya seçilmedi."),
    UPLOAD_DIR_NOT_EXIST(1023, "Yükleme dizini oluşturulamadı."),
    SAME_EMAIL(1024, "Bu emaile kayıtlı bir hesap bulunmaktadır."),
    SAME_TEL_NUMBER(1025, "Bu telefon numarasına kayıtlı bir hesap bulunmaktadır."),
    MAIL_NOT_ACCEPTED(1026, "Lütfen Mailinizi Onaylayınız."),
    USER_NAME_NOT_MATCH_REGEX(1027, "Lütfen Kullanıcı Adı Belirleme Kurallarına Uyunuz."),
    USER_PASS_NOT_MATCH_REGEX(1028, "Lütfen Şifre Belirleme Kurallarına Uyunuz."),
    USER_EMAIL_NOT_MATCH_REGEX(1029, "Lütfen Emailini Girerken Kurallarına Uyunuz."),
    USER_TEL_NUMBER_NOT_MATCH_REGEX(1030, "Lütfen Telefon Numarası Girerken Kurallarına Uyunuz."),
    TOKEN_EMPTY(1031, "Token boş olamaz."),
    NOT_FOLLOW(1032, "Bu kullanıcıyı takip etmiyorsunuz."),
    DOES_NOT_FOLLOW(1033, "Bu kullanıcı sizi takip etmiyor."),
    ROOM_NOT_FOUND(1034, "Oda boş olamaz."),
    NOT_ACCESS_OTHER_USER(1035, "Başka kullanıcı için işlem yapamazsınız.");

    private final int code;
    private final String message;

    ErrorMessages(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorMessages getByMessage(String message) {
        if (message != null) {
            for (ErrorMessages em : values()) {
                if (em.getMessage().equals(message))
                    return em;
            }
        }
        return null;
    }
}