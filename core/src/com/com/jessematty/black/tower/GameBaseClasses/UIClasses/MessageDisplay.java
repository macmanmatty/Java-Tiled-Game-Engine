package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.jessematty.black.tower.Maps.GameMap;

public class MessageDisplay {
private int messageNumber;
 private int optionNumber=-1;
private GameMap map;
private  Window window;
    private SplitPane splitpane;
    private Label fighter1message;
    private  Label fighter2Message;




/*
    public Window talk(String message, final Skin skin){

     window= new Window("", skin, "SpeechBubble");


       Label label = new Label(message , skin);

window.addEntity(label);
window.row();

window.pack();
return window;




    }

    public Window displayMessage(String message, final Fighters fighter, AtlasRegion region){
        map=fighter.getMapToTransportTo();
        this.fighter=fighter;
        Skin skin= map.getSkin();
        final Window window= new Window("message", skin);
        final HorizontalGroup hGroup= new HorizontalGroup();



        Label label = new Label(message , skin);
        TextButton close= new TextButton("close" , skin);
        close.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                window.removeEntity();




                return super.touchDown(event, x, y, pointer, button);
            }
        });
        hGroup.addActor(label);
        hGroup.addActor(new Image(new TextureRegionDrawable(region)));
        window.addEntity(hGroup);
        window.row();
        window.addActor(close);



        return window;

    }
    public Window talk( ArrayList<String> options, final String text , Skin skin){
        final ArrayList<TextButton> buttons =  new ArrayList<TextButton>(options.size());
        this.fighter=fighter;
        map=fighter.getMapToTransportTo();
window= new Window("message" ,skin, "TalkOptionWindow");



Label label= new Label(text, skin);
window.addEntity(label);
window.row();

        int size=options.size();

        for ( int count = 0; count<size; count++){
            buttons.addEntity( new OptionButton(options.get(count), skin, count));

            buttons.get(count).addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    window.removeEntity();
                    map.getDraw().resume();
                   OptionButton option= (OptionButton) event.getTarget();




                    setOptionNumber(option.getOption());
                    window.removeEntity();



                    return super.touchDown(event, x, y, pointer, button);
                }
            });

            window.addEntity(buttons.get(count));


        }









return window;


    }


    public Window displayMessage(ArrayList<String> messages, Fighters fighter){
        final ArrayList<Label> labels =  new ArrayList<Label>(messages.size());
        map=fighter.getMapToTransportTo();

        this.fighter=fighter;


        final Skin skin= map.getSkin();
      window= new Window("message", skin);



        int size=messages.size();

        for (int count=0; count<size; count++){
            labels.addEntity( new Label(messages.get(count), skin));

        }

        final TextButton close= new TextButton("close" , skin);

        close.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                window.removeEntity();
                map.getDraw().resume();



                return super.touchDown(event, x, y, pointer, button);
            }
        });


        window.addEntity(labels.get(0));
        window.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                labels.get(messageNumber).removeEntity();
                messageNumber++;
                window=new Window("message", skin);

                window.addEntity(labels.get(messageNumber));
                if(messageNumber==labels.size()-1){
                    window.row();
                   window.addEntity(close);

                }



                return super.touchDown(event, x, y, pointer, button);
            }
        });


      return window;

    }

    public void displayTalk(Fighters fighter1, final ArrayList<String> fighter1Messages, final ArrayList<String> fighter2Messages, Fighters fighter2, Skin skin){
   window= new Window(fighter1.getName(), skin);
   TextButton next= new TextButton("Next", skin);

   next.addListener(new ClickListener(){
       @Override
       public void clicked(InputEvent event, float x, float y) {
           super.clicked(event, x, y);
           if(fighter1Messages.size()==0){

               splitpane.removeEntity();

           }


           fighter1Messages.removeEntity(0);
           fighter2Messages.removeEntity(0);
           fighter1message.setText(fighter1Messages.get(0));
           fighter2Message.setText(fighter2Messages.get(0));




       }
   });
        fighter1message= new Label (fighter1Messages.get(0), skin);
        window.addEntity(fighter1message);
        fighter2Message= new Label(fighter2Messages.get(0), skin);
        window.addEntity(fighter2Message);



















    }






    public Window displayMessage(ArrayList<String> messages, Fighters fighter, ArrayList<AtlasRegion> regions){

        map=fighter.getMapToTransportTo();
        map.pauseGame();

        this.fighter=fighter;


        final Skin skin= map.getSkin();

        window= new Window("message", skin);
        final ArrayList<Label> labels =  new ArrayList<Label>(messages.size());
        final ArrayList<Image> images= new ArrayList<Image>(regions.size());


        int size=messages.size();

        for (int count=0; count<size; count++){
            labels.addEntity( new Label(messages.get(count), skin));

        }

        int size2=regions.size();
        for (int count=0; count<size2; count++){
            images.addEntity( new Image (new TextureRegionDrawable(regions.get(count))));

        }



        final TextButton close= new TextButton("close" , skin);

        close.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                window.removeEntity();
                map.getDraw().resume();



                return super.touchDown(event, x, y, pointer, button);
            }
        });

        HorizontalGroup hGroup= new HorizontalGroup();
        hGroup.addActor(images.get(0));
       window.addEntity(hGroup);
       window.row();
        hGroup.addActor(labels.get(0));
       window.addListener( new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                labels.get(messageNumber).removeEntity();
                messageNumber++;
                window= new Window("message", skin);

                window.addActor(labels.get(messageNumber));
                if(messageNumber==labels.size()-1){
                    window.row();
                    window.addEntity(close);

                }



                return super.touchDown(event, x, y, pointer, button);
            }
        });


        return window;

    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    */
}
