import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	String URL = "jdbc:mysql://localhost:3306/mhlzol004";
	Connection connect;
	Statement state;
	ResultSet results;
	String query;
	int updated = 0;
	String FileName;
	String table = "";
	passwordManager pm = new passwordManager();

	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection(URL, "mhlzol004", pm.getPassword("d}dokdp"));
			state = connect.createStatement();
			//state.executeUpdate("create database if not exists Networking;");
			state.executeUpdate("use mhlzol004;");
			query = "CREATE TABLE IF NOT EXISTS DATA(id INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(id),GroupID INT, Time VARCHAR(50), Temperature FLOAT(255,5), Light FLOAT(255,5));";
			state.executeUpdate(query);
			System.err.println("Endof database");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void Insert(int groupID, String Time, float Temperature, float light) throws SQLException{
		query = "INSERT INTO DATA" + "(GroupID,Time,Temperature,Light) VALUES"
				+ "(" + groupID + "," + "'" + Time + "'," + Temperature + ","
				+ light + ")";
		state.executeUpdate(query);
	}

	public String selectAll() {
		query = "SELECT * from  DATA";
		try {
			results = state.executeQuery(query);
			getResults();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}

	public String select(int GroupID) {
		query = "SELECT * from DATA WHERE GroupID=" + GroupID;
		try {
			results = state.executeQuery(query);
			getResults();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}
	public void mean (int parameter,int GroupID){
		if ( GroupID==0){
			query = "SELECT  AVG("+parameter+")from DATA";
		}
		else{
			query = "SELECT  AVG("+parameter+")from DATA WHERE GroupID=" + GroupID;
		}
	
		try {
			results = state.executeQuery(query);
			getResults();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void mode (int parameter,int GroupID){
		if ( GroupID==0){
			query = "SELECT +parameter"+"as key, count("+parameter+") AS qty" +
					"FROM DATA "+
					"GROUP BY key" +
					"ORDER BY qty DESC"+ 
					"LIMIT 1";
		}
		else{
			query = "SELECT +parameter"+"as key, count("+parameter+") AS qty" +
					"FROM DATA  WHERE GroupID=" + GroupID+
					"GROUP BY key" +
					"ORDER BY qty DESC"+ 
					"LIMIT 1";
			query = "SELECT  AVG("+parameter+")from DATA WHERE GroupID=" + GroupID;
	
	
		try {
			results = state.executeQuery(query);
			getResults();
		} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
	}

	public void getResults() throws SQLException {
		while (results.next()) {
			int numColumns = results.getMetaData().getColumnCount();
			for (int i = 1; i <= numColumns; i++) {
				table+=(results.getString(i) + " ");
			}
			if (!results.isLast()){
				table+="\n";
			}
		}
		results.close();

	}
}
