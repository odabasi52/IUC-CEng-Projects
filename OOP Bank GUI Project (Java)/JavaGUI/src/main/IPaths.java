package main;

import java.io.File;

public interface IPaths {
	String currentDirectory = System.getProperty("user.dir");
    File currentDir = new File(currentDirectory);
    File parentDir = currentDir.getParentFile();
    
	final String databasePath = parentDir.toString() + "\\JavaGUI\\gui.db";
	final String iconPath =  parentDir.toString() + "\\JavaGUI\\iconIUC.png";
}
