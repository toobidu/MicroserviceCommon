ALTER TABLE Users
    ADD Password VARCHAR2(50);

UPDATE Users
SET Password = CONCAT(username, 'Abc123!@#')
WHERE USER_ID IS NOT NULL;
-- Thêm ràng buộc NOT NULL
ALTER TABLE Users
    MODIFY Password NOT NULL;

-- Thêm ràng buộc CHECK để kiểm tra độ dài và định dạng password
ALTER TABLE Users
    ADD CONSTRAINT chk_password_format CHECK (
        LENGTH(Password) = 10
            AND REGEXP_LIKE(Password, '[A-Z]')
            AND REGEXP_LIKE(Password, '[0-9]')
            AND REGEXP_LIKE(Password, '[!@#$%^&*(),.?":{}|<>]')
        );