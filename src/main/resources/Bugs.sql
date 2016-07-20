#
# Select the number of open bugs for the given time period. Please note that input date value is an String with expected
# format to reduce errors because client localization.
#
# Tested using mysql 5.7.12
#
# @author Juan Rada
#
SELECT count(*) 'open bugs'
FROM bugs
WHERE STR_TO_DATE('01/01/2012', '%m/%d/%Y')
    BETWEEN open_date AND close_date;