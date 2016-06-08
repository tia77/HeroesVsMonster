import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Integer;

public class TorchGoesOut extends RoomCard{
   
   private int[] selectedNum = new int[3];
   private int selectedNumSel = -1;
   
   public TorchGoesOut(){
      super(11);
      active = true;
   }
   
   public void drawAction(Graphics2D g, int posX, int posY){
      super.drawAction(g, posX, posY);
   }
   public ActionCard processKeyInput(KeyEvent e){ 
      if(cardState == CardState.SHOW){
         if(e.getKeyCode() == KeyEvent.VK_ENTER ){
            active = false;
            return this;
         }
      }
      return super.processKeyInput(e);  
   }
   public ActionCard processMouseInput(Point screenSize, MouseEvent e){
      if(cardState == CardState.SHOW){
         if(GUI.hover((int)(screenSize.x/2)-25, (int)(screenSize.x/2)-25+75, (int)(screenSize.y-(3*screenSize.y/16))+10, (int)(screenSize.y-(3*screenSize.y/16))+30) ){
            active = false;
            return this;
         }
      }
      return super.processMouseInput(screenSize, e);
   }
   public boolean actionKeyInput(KeyEvent e, int count){
      
      
      if(selectedNumSel == 1 || selectedNumSel == 2 || selectedNumSel == 3){
         //TODO get wich one is click and put it in array
         //selectedNum[selectedNumSel-1] = ; //<- put the value here
         selectedNumSel++;
      }else{
         if(e.getKeyChar() == (count+"").charAt(0)){ //if you try
            System.out.println("Try to ligth your torch");
            selectedNumSel = 1;         
         }
      }
      if(selectedNumSel == 4){
         Random random = new Random();
         int dice = random.nextInt(6) + 1;
         for(int i=0; i<selectedNum.length; i++){
            if(selectedNum[i] == dice){  
               getRidOfCard = true;
               return true;
            }
         }
         selectedNum = new int[3];
         return true;
      }
      return false;
   }
   public boolean actionMouseInput(Point screenSize, Point actionPos, MouseEvent e, int count){
      
      if(selectedNumSel == 1 || selectedNumSel == 2 || selectedNumSel == 3){
         //TODO get wich one is click and put it in array
         //selectedNum[selectedNumSel-1] = ; //<- put the value here
         selectedNumSel++;
      }else{
         if(GUI.hover(actionPos.x, actionPos.x+140, actionPos.y+40+(25*count), actionPos.y+40+25+(25*count))){  //if you try
            System.out.println("Try to ligth your torch");
            selectedNumSel = 1;
            
         }
      }
      if(selectedNumSel == 4){
         Random random = new Random();
         int dice = random.nextInt(6) + 1;
         for(int i=0; i<selectedNum.length; i++){
            if(selectedNum[i] == dice){  
               getRidOfCard = true;
               return true;
            }
         }
         selectedNum = new int[3];
         return true;
      }
      return false;
   }
   
   public void tryToLigth(){
      Random random = new Random();
      int dice = random.nextInt(12) + 1;
      damage -= dice;
      getRidOfCard = true;
   }
   
   public String[] getPrintableAction(){
      if(selectedNumSel == 1 || selectedNumSel == 2 || selectedNumSel == 3){
        
         ArrayList<Integer> act = new ArrayList<Integer>();
         for(int i=1; i<=6; i++){
            boolean done = false;
            for(int j=0; j<selectedNum.length; j++)
               if(i != selectedNum[j])
                  done = true;
            if(done)
               act.add(i);
         }
         String[] actions = new String[act.size()]; 
         for (int i=0; i < actions.length; i++){
            actions[i] = " "+act.get(i).intValue();
         } 
          
         return actions;
      }else{
         String[] actions = {"Try to ligth your torch"};
         return actions;
      }
   }
   
   public boolean disableDefaultAction(){
      return true;
   }
}