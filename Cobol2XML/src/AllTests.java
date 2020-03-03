import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ ConstantValueTest.class, DivisionTests.class, SectionStorageTest.class, CobolTest.class })
public class AllTests
{

}
