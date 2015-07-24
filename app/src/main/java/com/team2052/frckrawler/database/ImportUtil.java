package com.team2052.frckrawler.database;

import com.google.gson.JsonObject;
import com.team2052.frckrawler.db.DaoSession;
import com.team2052.frckrawler.db.Event;
import com.team2052.frckrawler.db.Robot;
import com.team2052.frckrawler.db.RobotDao;
import com.team2052.frckrawler.db.RobotEvent;
import com.team2052.frckrawler.db.RobotEventDao;
import com.team2052.frckrawler.db.Team;
import com.team2052.frckrawler.tba.HTTP;
import com.team2052.frckrawler.tba.JSON;
import com.team2052.frckrawler.tba.TBA;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author Adam
 * @since 10/9/2014
 */
public class ImportUtil {
    public static void importTeamsFromCSV(DaoSession daoSession, File csv, Event event) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csv.getAbsolutePath()));
            List<String[]> strings = csvReader.readAll();
            List<Team> teams = new ArrayList<>();
            for (String[] stringArr : strings) {
                //Parse and get the teams
                int number = Integer.parseInt(stringArr[0]);
                String url = TBA.BASE_TBA_API_URL + String.format(TBA.TEAM, number);
                JsonObject teamJSONObject = JSON.getAsJsonObject(HTTP.dataFromResponse(HTTP.getResponse(url)));

                Team team;

                if (teamJSONObject.has("team_number")) {
                    team = JSON.getGson().fromJson(teamJSONObject, Team.class);
                } else {
                    team = new Team((long) number, "frc" + number, "Unknown", "Unknown");
                }

                daoSession.insertOrReplace(team);
                teams.add(team);
            }

            for (Team team : teams) {
                //Add the robots
                Robot robot = daoSession.getRobotDao().queryBuilder().where(RobotDao.Properties.Game_id.eq(event.getGame_id())).where(RobotDao.Properties.Team_id.eq(team.getNumber())).unique();

                if (robot == null) {
                    daoSession.getRobotEventDao().insert(new RobotEvent(null, daoSession.getRobotDao().insert(new Robot(null, team.getNumber(), event.getGame_id(), null, null)), event.getId(), null));
                } else {
                    List<RobotEvent> robotEvents = daoSession.getRobotEventDao().queryBuilder().where(RobotEventDao.Properties.Robot_id.eq(robot.getId())).where(RobotEventDao.Properties.Event_id.eq(event.getId())).list();
                    if (robotEvents.size() <= 0) {
                        daoSession.getRobotEventDao().insert(new RobotEvent(null, robot.getId(), event.getId(), null));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}