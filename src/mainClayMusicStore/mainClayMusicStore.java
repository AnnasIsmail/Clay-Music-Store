package mainClayMusicStore;

import Login.Login;
import MainForm.mainForm;
public class mainClayMusicStore extends Login{
public static void main(String[] args) {
	 try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
             if ("Nimbus".equals(info.getName())) {
                 javax.swing.UIManager.setLookAndFeel(info.getClassName());
                 break;
             }
         }
     } catch (ClassNotFoundException e) {
         java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
     } catch (InstantiationException e) {
         java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
     } catch (IllegalAccessException e) {
         java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
     } catch (javax.swing.UnsupportedLookAndFeelException e) {
         java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
     }
	new mainClayMusicStore();
}
}
