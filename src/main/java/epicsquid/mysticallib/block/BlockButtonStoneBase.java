package epicsquid.mysticallib.block;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.model.IModeledObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockButtonStoneBase extends BlockButtonStone implements IBlock, IModeledObject {
  private final @Nonnull
  Item itemBlock;
  public List<ItemStack> drops = null;
  private boolean isOpaque = false;
  private boolean hasCustomModel = false;
  private boolean isFlammable = false;
  private BlockRenderLayer layer = BlockRenderLayer.CUTOUT;
  private Block parent;
  public String name = "";

  public BlockButtonStoneBase(@Nonnull Block base, @Nonnull SoundType type, float hardness, @Nonnull String name) {
    super();
    this.parent = base;
    this.name = name;
    setCreativeTab(null);
    setTranslationKey(name);
    setRegistryName(LibRegistry.getActiveModid(), name);
    setSoundType(type);
    setLightOpacity(0);
    setHardness(hardness);
    setOpacity(false);
    this.fullBlock = false;
    itemBlock = new ItemBlock(this).setTranslationKey(name).setRegistryName(LibRegistry.getActiveModid(), name);
  }

  @Nonnull
  public BlockButtonStoneBase setFlammable(boolean flammable) {
    this.isFlammable = flammable;
    return this;
  }

  @Override
  @Nonnull
  public BlockButtonStoneBase setResistance(float resistance) {
    super.setResistance(resistance);
    return this;
  }

  @Nonnull
  public BlockButtonStoneBase setModelCustom(boolean custom) {
    this.hasCustomModel = custom;
    return this;
  }

  @Nonnull
  public BlockButtonStoneBase setHarvestReqs(String tool, int level) {
    setHarvestLevel(tool, level);
    return this;
  }

  @Nonnull
  public BlockButtonStoneBase setOpacity(boolean isOpaque) {
    this.isOpaque = isOpaque;
    return this;
  }

  @Nonnull
  public BlockButtonStoneBase setLayer(@Nonnull BlockRenderLayer layer) {
    this.layer = layer;
    return this;
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean isOpaqueCube(@Nonnull IBlockState state) {
    return isOpaque;
  }

  public boolean hasCustomModel() {
    return this.hasCustomModel;
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean isFullCube(@Nonnull IBlockState state) {
    return false;
  }

  @Override
  public boolean canPlaceTorchOnTop(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
    return true;
  }

  @Override
  public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
    if (tab == this.getCreativeTab()) {
      list.add(new ItemStack(this, 1));
    }
  }

  @Override
  public boolean isFlammable(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EnumFacing face) {
    return isFlammable || super.isFlammable(world, pos, face);
  }

  @Override
  public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
    return isFlammable ? 100 : super.getFlammability(world, pos, face);
  }

  @Override
  @SideOnly(Side.CLIENT)
  @Nonnull
  public BlockRenderLayer getRenderLayer() {
    return this.layer;
  }

  @Override
  @Nonnull
  public Item getItemBlock() {
    return itemBlock;
  }

  @Override
  @SuppressWarnings("deprecation")
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(itemBlock);
  }

  @Nonnull
  public Block getParent() {
    return parent;
  }

  @Override
  public void initModel() {
    ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
  }
}
