package test;

import legal.Service.LegalProcess.DocFileProcessService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Created by Son on 4/17/2017.
 */
@RunWith(Arquillian.class)
public class DocFileProcessServiceTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DocFileProcessService.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
