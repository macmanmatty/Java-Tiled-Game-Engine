package com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.BasicLists;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.ObservableList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
public class AddRemoveList extends VerticalGroup {
   private  Skin skin;
    private SelectBox<String> itemsList;
    private Array<String> items;
    private Button add;
    private Button remove;
    private Button removeAll;
    private TextField nameField;
    private Label title;
    private String titleText;
    public AddRemoveList(  Skin skin,  String title) {
        this.skin=skin;
        this.titleText=title;
    }
    public AddRemoveList(Skin skin, String titleText, Array<String> items) {
        this.skin = skin;
        this.items = items;
        this.titleText=titleText;
        makeGroup();
    }
    public void   makeGroup(){
        clear();
        this.itemsList=new SelectBox<String>(skin);
        this.itemsList.getItems().clear();
        this.itemsList.getItems().addAll(items);
        this.title= new Label(titleText, skin);
        title.setAlignment(Align.top);
        add= new TextButton("Add", skin);
        remove= new TextButton("Remove", skin);
        nameField= new TextField("Enter Text", skin);
        removeAll= new TextButton("Clear", skin);
        final NamedField namedField= new NamedField(nameField, new Label("Enter Text ", skin));
        add.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                items.add(namedField.getField().getText());
            return  true;
            }
        });
       remove.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                items.removeIndex(itemsList.getSelectedIndex());
                return  true;
            }
        });
        removeAll.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                items.clear();
                return  true;
            }
        });
        HorizontalGroup buttons = new HorizontalGroup();
        buttons.addActor(add);
        buttons.addActor(remove);
        buttons.addActor(removeAll);
        buttons.space(5);
        addActor(title);
        addActor(itemsList);
        addActor(namedField);
        addActor(buttons);
  }
    public Array<String> getItems() {
        return items;
    }
    public void setItems(Array<String> items) {
        if(items==null){
            return;
        }
        this.items = items;
        makeGroup();
    }
    public String getTitleText() {
        return titleText;
    }
    public void setTitleText(String titleText) {
        if(titleText==null){
            return;
        }
        this.titleText = titleText;
        makeGroup();
    }
}
