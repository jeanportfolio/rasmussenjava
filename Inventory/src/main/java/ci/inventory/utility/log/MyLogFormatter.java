package ci.inventory.utility.log;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;


public class MyLogFormatter extends Formatter{

	@Override
	public String format(LogRecord record) {
		return record.getLongThreadID()+"::"+record.getLevel()+
				"::"+ (record.getThrown() != null ? record.getThrown().getClass().getSimpleName() : "No Thrown class" )+
				"::"+record.getSourceClassName()+"::"
                +record.getSourceMethodName()
                +"::"+new Date(record.getMillis())+
                "::"+record.getMessage()+"\n";
	}
}
