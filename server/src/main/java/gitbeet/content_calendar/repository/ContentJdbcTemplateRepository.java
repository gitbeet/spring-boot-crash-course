package gitbeet.content_calendar.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import gitbeet.content_calendar.model.Content;
import gitbeet.content_calendar.model.Status;
import gitbeet.content_calendar.model.Type;

public class ContentJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // mapRow converts all the results we get from the database to a Content object
    private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("description"),
            Status.valueOf(rs.getString("status")), // Convert String to Enum
            Type.valueOf(rs.getString("content_type")),
            rs.getTimestamp("date_created").toLocalDateTime(), // Convert Timestamp → LocalDateTime
            rs.getTimestamp("date_updated") != null 
                ? rs.getTimestamp("date_updated").toLocalDateTime() 
                : null, // Handle possible null
            rs.getString("url")
        );
    }

    public List<Content> getAllContent(){
        String sql = "SELECT * FROM Content";
        List<Content> contents = jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
        return contents;
    }

    public void updateContent(int id, String title, String desc, Status status, Type contentType, String URL) {
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL, id);
    }

    public void deleteContent(int id) {
        String sql = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Content getContent(int id) {
        String sql = "SELECT * FROM Content WHERE id=?";
        Content content = jdbcTemplate.queryForObject(sql, new Object[]{id}, ContentJdbcTemplateRepository::mapRow);
        return content;
    }
}
