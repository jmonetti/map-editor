package comandosMenu;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class ComandoAbrirImagen extends ComandoMenu {

	
	public void ejecutar() {
		JFileChooser ventanaArchivos = new JFileChooser();
		ventanaArchivos.setFileFilter(new FiltroArchivos());
		ventanaArchivos.setAcceptAllFileFilterUsed(false);
		int opcion = ventanaArchivos.showOpenDialog(null);
		if(opcion == JFileChooser.APPROVE_OPTION){
			System.out.println("Abrio archivo");
		}
		
			
		
	}
	
	private class FiltroArchivos extends FileFilter{
		public boolean accept(File arg0) {
			if(arg0.isDirectory() || !arg0.exists() )
				return true;
			else
				if(getExtension(arg0).equals("gif") || getExtension(arg0).equals("jpg"))
					return true;
				else
					return false;
					
		}
		private String getExtension(File f) 
		{
			String s = f.getName();
			int i = s.lastIndexOf('.');
			if (i > 0 &&  i < s.length() - 1) 
				return s.substring(i+1).toLowerCase();
			return "";
		}
		public String getDescription() {
			return "*.jgp, *.gif";
		}
	}
}
