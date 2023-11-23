package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMaskException;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.NumberedTile;
import com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles.TileSet;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.Generators.Sets.MaskMode;

public class TiledMapTileSetUtilities {

    public static TileSet createTileSet(GameAssets assets, String atlasName, String tileName, MaskMode mode, Array<Integer> bitNumbers, NamedColor color){
        TileSet tileSet= new TileSet();
        tileSet.setMaskMode(mode);
        tileSet.setColor(color);
        tileSet.setAtlasName(atlasName);
        setTileNumbers(assets, tileName, atlasName, bitNumbers, mode);
        return  tileSet;
    }

    private static  void setTileNumbers( GameAssets assets, String tileName, String atlasName, Array<Integer>  maskNumbers, MaskMode maskMode)  {
        ObjectMap<Integer, Array<String>> setNumbers = new ObjectMap<>(); // number of images for each mask number

        switch (maskMode) {


            case FULL_WANG_SET:
                Array<String> tiles2= new Array();
                setNumbers.put(2, tiles2);
                makeRegionNames(tiles2, tileName, 2, atlasName, assets);
                Array<String> tiles8= new Array();
                setNumbers.put(8, tiles8);
                makeRegionNames(tiles8, tileName, 8, atlasName, assets);
                Array<String> tiles10= new Array();
                setNumbers.put(10, tiles10);
                makeRegionNames(tiles10, tileName, 10, atlasName, assets);
                Array<String> tiles16= new Array();
                setNumbers.put(16, tiles16);
                makeRegionNames(tiles16, tileName, 16, atlasName, assets);
                Array<String> tiles18= new Array();
                setNumbers.put(18, tiles18);
                makeRegionNames(tiles18, tileName, 18, atlasName, assets);

                Array<String> tiles24= new Array();
                setNumbers.put(24, tiles24);
                makeRegionNames(tiles24, tileName, 24, atlasName, assets);
                Array<String> tiles26= new Array();
                setNumbers.put(26, tiles26);
                makeRegionNames(tiles26, tileName, 26, atlasName, assets);
                Array<String> tiles64= new Array();
                setNumbers.put(64, tiles64);
                makeRegionNames(tiles64, tileName, 64, atlasName, assets);
                Array<String> tiles66= new Array();
                setNumbers.put(66, tiles66);
                makeRegionNames(tiles66, tileName, 66, atlasName, assets);
                Array<String> tiles72= new Array();
                setNumbers.put(72, tiles72);
                makeRegionNames(tiles72, tileName, 72, atlasName, assets);
                Array<String> tiles74= new Array();
                setNumbers.put(74, tiles74);
                makeRegionNames(tiles74, tileName, 74, atlasName, assets);
                Array<String> tiles80= new Array();
                setNumbers.put(80, tiles80);
                makeRegionNames(tiles80, tileName, 80, atlasName, assets);
                Array<String> tiles82= new Array();
                setNumbers.put(82, tiles82);
                makeRegionNames(tiles82, tileName, 82, atlasName, assets);
                Array<String> tiles88= new Array();
                setNumbers.put(88, tiles88);
                makeRegionNames(tiles88, tileName, 88, atlasName, assets);

                Array<String> tiles90= new Array();
                setNumbers.put(90, tiles90);
                makeRegionNames(tiles90, tileName, 90, atlasName, assets);




            case PARTIAL_WANG_SET:


                Array<String> tiles126= new Array();
                setNumbers.put(126, tiles126);
                makeRegionNames(tiles126, tileName, 126, atlasName, assets);

                Array<String> tiles219= new Array();
                setNumbers.put(219, tiles219);
                makeRegionNames(tiles219, tileName, 219, atlasName, assets);


            case PARTIAL_WANG_SET_NO_DIAGONALS:
                Array<String> tiles0= new Array();
                setNumbers.put(0, tiles0);
                makeRegionNames(tiles0, tileName, 0, atlasName, assets);

                Array<String> tiles11= new Array();
                setNumbers.put(11, tiles11);
                makeRegionNames(tiles11, tileName, 11, atlasName, assets);
                Array<String> tiles22= new Array();
                setNumbers.put(22, tiles22);
                makeRegionNames(tiles22, tileName, 22, atlasName, assets);
                Array<String> tiles31= new Array();
                setNumbers.put(31, tiles31);
                makeRegionNames(tiles31, tileName, 31, atlasName, assets);



                Array<String> tiles104= new Array();
                setNumbers.put(104, tiles104);
                makeRegionNames(tiles104, tileName, 104, atlasName, assets);

                Array<String> tiles107= new Array();
                setNumbers.put(107, tiles107);
                makeRegionNames(tiles107, tileName, 107, atlasName, assets);




                Array<String> tiles127= new Array();
                setNumbers.put(127, tiles127);
                makeRegionNames(tiles127, tileName, 127, atlasName, assets);


                Array<String> tiles208= new Array();
                setNumbers.put(208, tiles208);
                makeRegionNames(tiles208, tileName, 208, atlasName, assets);




                Array<String> tiles214= new Array();
                setNumbers.put(214, tiles214);
                makeRegionNames(tiles214, tileName, 214, atlasName, assets);




                Array<String> tiles223= new Array();
                setNumbers.put(223, tiles223);
                makeRegionNames(tiles223, tileName, 223, atlasName, assets);

                Array<String> tiles248= new Array();
                setNumbers.put(248, tiles248);
                makeRegionNames(tiles248, tileName, 248, atlasName, assets);


                Array<String> tiles251= new Array();
                setNumbers.put(251, tiles251);
                makeRegionNames(tiles251, tileName, 251, atlasName, assets);


                Array<String> tiles253= new Array();
                setNumbers.put(253, tiles253);
                makeRegionNames(tiles253, tileName, 253, atlasName, assets);

                Array<String> tiles255= new Array();
                setNumbers.put(255, tiles255);
                makeRegionNames(tiles255, tileName, 255, atlasName, assets);



                break;

            case CUSTOM:
                int size = maskNumbers.size;
                if(maskNumbers.size==0){
                throw  new BitMaskException("Must Have Numbers to Mask");

            }

                if(!InList.isInList(maskNumbers, new Integer(255))){
                    throw  new BitMaskException("Missing  bit number  255 ");

                }

                for (int count = 0; count < size; count++) {
                    setNumbers.put(maskNumbers.get(count), new Array<String>());


                }


                break;
        }

    }

    private static void  makeRegionNames( Array<String> tileNames , String tileName , int bitNumber,  String atlasName,  GameAssets assetts){
       int count=0;
      String fullTileName = tileName + "." + bitNumber + count;

        AtlasNamedAtlasRegion region = assetts.getAtlasRegionByName(fullTileName, atlasName);
        if(region!=null){

            tileNames.add(fullTileName);
        }
        while(region!=null) {
            count++;

         fullTileName = tileName + "." + bitNumber + count;
          region = assetts.getAtlasRegionByName(fullTileName, atlasName);
          if(region!=null) {
              tileNames.add(fullTileName);
          }


        }


    }

    public boolean setCheck( TileSet tileSet, MaskMode maskMode){


        ObjectMap<Integer, Array<NumberedTile>> setTileNames=tileSet.getSetRegionNames();

        if(setTileNames.size==0){


            return  false;

        }
        switch (maskMode){

            case FULL_WANG_SET:
                if(setTileNames.get(2).size<1){
                    return  false;

                }

                if(setTileNames.get(8).size<1){
                    return  false;

                }
                if(setTileNames.get(10).size<1){
                    return  false;

                }
                if(setTileNames.get(16).size<1){
                    return  false;

                }
                if(setTileNames.get(18).size<1){
                    return  false;

                }
                if(setTileNames.get(24).size<1){
                    return  false;

                }
                if(setTileNames.get(26).size<1){
                    return  false;

                }
                if(setTileNames.get(64).size<1){
                    return  false;

                }
                if(setTileNames.get(66).size<1){
                    return  false;

                }
                if(setTileNames.get(72).size<1){
                    return  false;

                }
                if(setTileNames.get(74).size<1){
                    return  false;

                }
                if(setTileNames.get(80).size<1){
                    return  false;

                }
                if(setTileNames.get(82).size<1){
                    return  false;

                }
                if(setTileNames.get(88).size<1){
                    return  false;

                }

                if(setTileNames.get(90).size<1){
                    return  false;

                }



            case PARTIAL_WANG_SET:


                if(setTileNames.get(126).size<1){
                    return  false;

                }

                if(setTileNames.get(127).size<1){
                    return  false;

                }

            case PARTIAL_WANG_SET_NO_DIAGONALS:

                if(setTileNames.get(11).size<1){
                    return  false;

                }
                if(setTileNames.get(22).size<1){
                    return  false;

                }
                if(setTileNames.get(31).size<1){
                    return  false;

                }

                if(setTileNames.get(104).size<1){
                    return  false;

                }

                if(setTileNames.get(107).size<1){
                    return  false;

                }


                if(setTileNames.get(127).size<1){
                    return  false;

                }

                if(setTileNames.get(208).size<1){
                    return  false;

                }


                if(setTileNames.get(214).size<1){
                    return  false;

                }



                if(setTileNames.get(223).size<1){
                    return  false;

                }
                if(setTileNames.get(248).size<1){
                    return  false;

                }

                if(setTileNames.get(251).size<1){
                    return  false;

                }

                if(setTileNames.get(253).size<1){
                    return  false;

                }
                if(setTileNames.get(255).size<1){
                    return  false;

                }

                break;

            case CUSTOM:

                break;

        }

        return  true;
    }


}
