package golaxy.temp;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.golaxy.Application;
import com.golaxy.service.PingtaiInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PingtaiInfo.class, Application.class }, webEnvironment = WebEnvironment.NONE)
public class PingtaiInfo {
	private PingtaiInfoService pis;

	public void pingtaiInfo() {
	}
}
