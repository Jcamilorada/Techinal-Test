#
# Capitalize each word of the given phrase. An interation is performed over each phrase character when space is found.
# Next letter is capitalized.
#
# Tested using mysql 5.7.12
#
# @author Juan Rada
#
DELIMITER //
CREATE FUNCTION CAPITALIZE(phrase VARCHAR(200)) RETURNS varchar(200)
    BEGIN
        DECLARE length INT;
        DECLARE currentIndex INT;
        DECLARE capitalizedPhrase VARCHAR(200);

        SET length   = CHAR_LENGTH(phrase);
        SET capitalizedPhrase = CONCAT(UPPER(LEFT(phrase, 1)), SUBSTRING(LOWER(phrase), 2));
        SET currentIndex = 1;

        WHILE(currentIndex < length) DO
            IF (MID(capitalizedPhrase, currentIndex, 1) = ' ') THEN
                SET capitalizedPhrase =
                    CONCAT(
                        LEFT(capitalizedPhrase, currentIndex),
                        UPPER(MID(capitalizedPhrase, currentIndex + 1, 1)),
                        RIGHT(capitalizedPhrase, length - currentIndex - 1));
            END IF;
            set currentIndex = currentIndex + 1;
        END WHILE;

        RETURN capitalizedPhrase;

    END
    //
DELIMITER ;
