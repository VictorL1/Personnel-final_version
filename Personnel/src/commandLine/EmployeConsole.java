package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.sql.SQLException;
import java.time.LocalDate;

import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.Employe;
import personnel.ErreurDateDepart;
import personnel.ErreurDateFin;
import personnel.SauvegardeImpossible;

public class EmployeConsole 
{
	private Option afficher(final Employe employe)
	{
		return new Option("Afficher l'employ?", "l", () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("G?rer le compte " + employe.getNom(), "c");
			menu.add(afficher(employe));
			menu.add(changerNom(employe));
			menu.add(changerPrenom(employe));
			menu.add(changerMail(employe));
			menu.add(changerPassword(employe));
			menu.add(changerDateDebut(employe));
			menu.add(changeDateFin(employe));
			menu.add(SupprimerEmploye(employe));
			menu.addBack("q");
			return menu;
	}
	
	private Option SupprimerEmploye(final Employe employe)
	{
		return new Option("Supprimer un employe", "s", () -> {
			try {
				employe.remove();
			} catch (Exception e) {
				System.out.println("Impossible de supprimer l'employe");
			}
			
			});
	}

	private Option changeDateFin(final Employe employe) {
		return new Option("Changer Date d?part", "f", 
				() -> {
					try {
						try {
							System.out.println("Date de d?part actuel : " + employe.getDateFin());
							employe.setDateFin((LocalDate)LocalDate.parse(getString("Nouvelle Date de d?part : ")));
						}catch (ErreurDateFin f)
						{
							System.out.println(f);
						}
					} catch (Exception e) {
						System.out.println("erreur de format dans l'insertion de la date");
					}
				}
			);
	}

	private Option changerDateDebut(final Employe employe) {
		return new Option("Changer Date d'arriv?e", "d", 
				() -> {
					try {
						try {
							System.out.println("Date d'arriv?e actuel : " + employe.getDateDebut());
							employe.setDateDebut((LocalDate)LocalDate.parse(getString("Nouvelle Date d'arriv?e :")));
						} catch (ErreurDateDepart e) {
							System.out.println(e);
						}
					} catch (Exception e) {
						System.out.println("erreur de format dans l'insertion de la date");
					}
				}
			);
	}

	private Option changerNom(final Employe employe)
	{
		return new Option("Changer le nom", "n", 
				() -> {
					System.out.println("Nom actuel : " + employe.getNom());
					try {
						employe.setNom(getString("Nouveau nom : "));
					} catch (SauvegardeImpossible e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
			);
	}
	
	private Option changerPrenom(final Employe employe)
	{
		return new Option("Changer le pr?nom", "p", () -> {
			System.out.println("Pr?nom actuel : " + employe.getPrenom());
			try {
				employe.setPrenom(getString("Nouveau pr?nom : "));
			} catch (SauvegardeImpossible e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
	}
	
	private Option changerMail(final Employe employe)
	{
		return new Option("Changer le mail", "e", () -> {
			System.out.println("Mail actuel : " + employe.getMail());
			try {
				employe.setMail(getString("Nouveau mail : "));
			} catch (SauvegardeImpossible e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
	}
	
	private Option changerPassword(final Employe employe)
	{
		return new Option("Changer le password", "x", () -> {try {
			employe.setPassword(getString("Nouveau password : "));
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
	}
	

}
