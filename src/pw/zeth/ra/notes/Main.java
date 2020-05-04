package pw.zeth.ra.notes;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pw.zeth.ra.notes.commands.Delnote;
import pw.zeth.ra.notes.commands.Note;
import pw.zeth.ra.notes.commands.Notes;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		final FileConfiguration config = this.getConfig();

		Date datenow = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("MMddyyyyHHmmss");

		config.options().copyDefaults(true);
		saveConfig();

		String defaultconfigcheck = this.getConfig().getString("notes");
		if (defaultconfigcheck.indexOf("none") >=0) {
			System.out.println("Skipping note date check");
		} else {

			for (String uuid : this.getConfig().getConfigurationSection("notes").getKeys(false)) {
				for (String notedate : this.getConfig().getConfigurationSection("notes." + uuid).getKeys(false)) {

					if (!this.getConfig().isSet("notes." + uuid + "." +  notedate + ".old")) {

						String notedate1 = notedate.replace("/", "");
						String notedate2 = notedate1.replace("-", "");
						String cutnotedate = notedate2.replace(";", "");
						BigInteger finalnotedate = new BigInteger(cutnotedate);
						BigInteger firstcurrentdate = new BigInteger(dateformat.format(datenow));
						BigInteger modifierdate = new BigInteger("3000000000000");
						BigInteger finaldate = finalnotedate.add(modifierdate);
						int dateres = finaldate.compareTo(firstcurrentdate);

						if (dateres == 0) {

							System.out.println("[Notes] Marking note as old: " + uuid + " - " + notedate);
							this.getConfig().set("notes." + uuid + "." + notedate + ".old", "1");

						} else if (dateres == -1) {

							System.out.println("[Notes] Marking note as old: " + uuid + " - " + notedate);
							this.getConfig().set("notes." + uuid + "." + notedate + ".old", "1");

						}
					}
				}
			}
		}

		saveConfig();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Note(this), this);

		getCommand("note").setExecutor(new Note(this));
		getCommand("notes").setExecutor(new Notes(this));
		getCommand("delnote").setExecutor(new Delnote(this));
		//TODO Make OLD (3m) and DELETED(+USER) note tag
	}

	@Override
	public void onDisable() {
	}

}
