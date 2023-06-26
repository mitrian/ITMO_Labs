//package com.mitrian.common.commands.cmdclass;
//
//import com.mitrian.common.commands.AbstractCommand;
//import com.mitrian.common.commands.util.ExecutionResult;
//import com.mitrian.common.commands.util.ExecutionStatus;
//
//import java.io.IOException;
//import java.util.List;
//
//public class SaveCommand extends AbstractCommand {
//
//    /**
//     * Constructor for initialize fields
//     * @param arguments param for initialize arguments field
//     */
//    public SaveCommand(List<String> arguments) {
//        super(0, arguments, false);
//    }
//
//    public SaveCommand() {
//    }
//
//
//    /**
//     * Executing command
//     * @return status of executing
//     */
//    @Override
//    public ExecutionResult execute() {
//        try
//        {
//            dao.save();
//            return new ExecutionResult(ExecutionStatus.SUCCEED)
//                    .append("Collection successfully saved");
//        }
//        catch (IOException e)
//        {
//            return new ExecutionResult(ExecutionStatus.FAILED)
//                    .append("Failed to save the collection")
//                    .append(e.getMessage());
//        }
//    }
//
//
//    /**
//     * Getter name of command
//     */
//    public String getNameOfCommand(){
//        return "save";
//    }
//}
