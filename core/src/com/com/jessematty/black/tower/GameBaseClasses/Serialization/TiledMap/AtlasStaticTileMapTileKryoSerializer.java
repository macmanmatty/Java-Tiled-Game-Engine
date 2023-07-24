package com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;

/**
custom kryo serializer for  a tiled  map
 */
public class AtlasStaticTileMapTileKryoSerializer extends Serializer<AtlasStaticTiledMapTile> {
    private final GameAssets gameAssets;
    public AtlasStaticTileMapTileKryoSerializer(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }
    @Override
    public void write(Kryo kryo, Output output, AtlasStaticTiledMapTile tile) {
        kryo.writeClassAndObject(output, tile);






    }
    @Override
    public AtlasStaticTiledMapTile read(Kryo kryo, Input input, Class<AtlasStaticTiledMapTile> type) {
        AtlasStaticTiledMapTile tile= (AtlasStaticTiledMapTile) kryo.readClassAndObject(input);
        tile.setTextureRegion(gameAssets.getAtlasRegionByName(tile.getNames()[0]));
        return  tile;
    }
}
