#
# Use this query if rank is unique for each position.
#
# Tested using mysql 5.7.12
#
# @author Juan Rada
#
SELECT
    @rownum:=@rownum + 1 'rank', v.name, v.votes
FROM votes v, (SELECT @rownum:=0) r
    ORDER BY 3;

#
# Use this query two candidates can share the same position
#
# Tested using mysql 5.7.12
#
# @author Juan Rada
#
SELECT
    IF(@prev_val = v.votes, @rownum, @rownum:= @rownum + 1) 'rank',
    v.name,
    @prev_val := v.votes 'votes'
FROM votes v, (SELECT @rownum:=0) r, (SELECT @prev_val = NULL) p
    ORDER BY 3;