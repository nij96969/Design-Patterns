interface  GameCharacter{
    String getDescription();
    int getPower();
}

class BaseCharacter implements GameCharacter {
    @Override
    public String getDescription() {
        return "Base Character | ";
    }

    @Override
    public int getPower() {
        return 10;
    }
}

abstract class CharacterDecortaor implements GameCharacter{
    protected GameCharacter character;

    public CharacterDecortaor(GameCharacter character){
        this.character = character;
    }

    @Override
    public String getDescription() {
        return character.getDescription();
    }

    @Override
    public int getPower() {
        return character.getPower();
    }
}

class ArmourDecorator extends CharacterDecortaor{
    ArmourDecorator(GameCharacter character){
        super(character);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "with Armour | ";
    }

    @Override
    public int getPower() {
        return super.getPower() + 50;
    }
}
class SwordDecorator extends CharacterDecortaor{
    SwordDecorator(GameCharacter character){
        super(character);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "with Sword | ";
    }

    @Override
    public int getPower() {
        return super.getPower() + 90;
    }
}
class SpeedDecorator extends CharacterDecortaor{
    SpeedDecorator(GameCharacter character){
        super(character);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "with Speed | ";
    }

    @Override
    public int getPower() {
        return super.getPower() + 20;
    }
}

public class GameCharacterClient {
    public static void main(String[] args) {
        GameCharacter base_Character = new BaseCharacter();

        GameCharacter with_armour_with_sword = new ArmourDecorator(new SwordDecorator(base_Character));

        GameCharacter with_speed_with_armour_with_sword = new SpeedDecorator(new ArmourDecorator(new SwordDecorator(base_Character)));

        GameCharacter with_sword_with_speed_with_armour = new SwordDecorator(new SpeedDecorator(new ArmourDecorator(base_Character)));

        System.out.println(with_armour_with_sword.getDescription());
        System.out.println(with_armour_with_sword.getPower());

        System.out.println(with_speed_with_armour_with_sword.getDescription());
        System.out.println(with_speed_with_armour_with_sword.getPower());

        System.out.println(with_sword_with_speed_with_armour.getDescription());
        System.out.println(with_sword_with_speed_with_armour.getPower());
    }
}
