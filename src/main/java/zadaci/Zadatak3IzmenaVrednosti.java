package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {

    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;
    public static void main(String[] args) {

        ConnectionSource connectionSource = null;


        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");


            avionDao= DaoManager.createDao(connectionSource, Avion.class);
            robaDao=DaoManager.createDao(connectionSource, Roba.class);




        List<Roba> robaZaIzmenu=robaDao.queryForEq(Roba.POLJE_OPIS,"Plasticna stolica");
        for(Roba r:robaZaIzmenu){
            r.setOpis("Drvena stolica");
            robaDao.update((Roba)robaZaIzmenu);

        }
        List<Roba> izmenjenaRoba= robaDao.queryForAll();
            for(Roba r:izmenjenaRoba){
                System.out.println(r);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
