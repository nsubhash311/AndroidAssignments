import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.androidassignments.ChatDatabaseHelper;

@RunWith(AndroidJUnit4.class)
public class ChatDatabaseHelperTest {

    private ChatDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    @Before
    public void setUp() {
        dbHelper = new ChatDatabaseHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        database = dbHelper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        dbHelper.close();
    }

    @Test
    public void onCreate_CreatesTable() {
        // Verify that the table doesn't exist before calling onCreate
        assertFalse(tableExists(database, ChatDatabaseHelper.TABLE_NAME));

        // Call onCreate to create the table
        dbHelper.onCreate(database);

        // Verify that the table has been created
        assertTrue(tableExists(database, ChatDatabaseHelper.TABLE_NAME));
    }

    private boolean tableExists(SQLiteDatabase db, String tableName) {
        String query = "SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" + tableName + "'";
        try (android.database.Cursor cursor = db.rawQuery(query, null)) {
            return cursor.getCount() > 0;
        }
    }
}
