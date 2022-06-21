package util.gui.validator.in.table;

public class ConstantTableValidatorImpl implements ITableValidator{
    final String NAME = "([a-zA-Z](([a-zA-Z]|\\d)+))|([a-zA-Z])";
    final String VALUE = "\\d+";
    @Override
    public boolean validate() {
        return validateName(null);
    }

    private boolean validateName(String name){
        if (name.matches(NAME)){
            return true;
        }
        return false;
    }

    private boolean validateValue(String value){
        if (value.matches(VALUE)){
            return Integer.parseInt(value) >= 0;
        }
        return false;
    }

    public static void main(String[] args) {
        ConstantTableValidatorImpl constantTableValidator = new ConstantTableValidatorImpl();
        System.out.println(constantTableValidator.validateName("Ff3"));
    }
}
