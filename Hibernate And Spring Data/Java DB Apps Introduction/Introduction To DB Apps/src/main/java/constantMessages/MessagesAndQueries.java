package constantMessages;

public class MessagesAndQueries {
    public static final String SELECT_GET_VILLAINS_NAME_BY_COUNT_QUERY = "SELECT v.name, COUNT(mv.minion_id) AS `count` " +
            "FROM villains v JOIN minions_villains mv " +
            "ON v.id = mv.villain_id " +
            "GROUP BY v.name " +
            "HAVING `count` > ? " +
            "ORDER BY `count` DESC;";

    public static final String DEFAULT_SELECT_VILLAIN_NAME_QUERY = "SELECT v.name FROM villains v WHERE v.id = ?;";

    public static final String DEFAULT_SELECT_VILLAIN_MINIONS_NAME_QUERY = "SELECT m.name, m.age\n" +
            "FROM minions m\n" +
            "JOIN minions_villains mv on m.id = mv.minion_id\n" +
            "WHERE mv.villain_id = ?;\n";

    public static final String DEFAULT_SELECT_TOWN_QUERY = "SELECT t.name FROM towns t WHERE t.name = ?;";

    public static final String DEFAULT_INSERT_TOWN_QUERY = "INSERT INTO towns  (name, country) VALUES (?, 'MagicPlace');";

    public static final String DEFAULT_INSERT_TOWN_QUERY_RESULT = "Town %s was added to the database.";

    public static final String DEFAULT_SELECT_VILLAIN_QUERY = "SELECT v.name FROM villains v WHERE v.name = ?;;";

    public static final String DEFAULT_INSERT_VILLAIN_QUERY = "INSERT INTO villains  (name, evilness_factor) VALUES (?, 'evil');";

    public static final String DEFAULT_INSERT_VILLAIN_QUERY_RESULT = "Villain %s was added to the database.";

    public static final String DEFAULT_INSERT_MINION_QUERY = "INSERT INTO minions (name, age, town_id) VALUES (?, ?, (SELECT t.id FROM towns t WHERE t.name = ?));";

    public static final String DEFAULT_INSERT_MINION_TO_VILLAIN_QUERY = "INSERT INTO minions_villains (minion_id, villain_id) " +
            "VALUES ((SELECT m.id FROM minions m WHERE m.name = ?), " +
            "(SELECT v.id FROM villains v WHERE v.name = ?))";

    public static final String DEFAULT_INSERT_MINION_TO_VILLAIN_QUERY_RESULT = "Successfully added %s to be minion of %s.";

    public static final String DEFAULT_CHANGE_TOWN_CASING_QUERY = "UPDATE towns t SET t.name = UPPER(t.name) WHERE t.country = ?;";

    public static final String DEFAULT_AFFECTED_TOWNS_QUERY = "SELECT t.name FROM towns t WHERE t.country = ?;";

    public static final String DELETE_VILLAIN_FROM_VILLAINS_TABLE_QUERY = "DELETE FROM villains WHERE id = ?";

    public static final String DELETE_VILLAIN_FROM_MINIONS_VILLAINS_TABLE_QUERY = "DELETE FROM minions_villains WHERE villain_id = ?";


}
