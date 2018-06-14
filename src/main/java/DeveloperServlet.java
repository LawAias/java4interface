import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DeveloperServlet", urlPatterns = {ConstantUtil.ALL_TESTS_URL,
        ConstantUtil.QUERY_TEST_URL, ConstantUtil.ADD_TEST_URL, ConstantUtil.UPDATE_TEST_URL,
        ConstantUtil.DELETE_TEST_URL})
public class DeveloperServlet extends HttpServlet {

    TestBusiness testBusiness = new TestBusiness();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("url = " + req.getRequestURI());
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=UTF-8");
        String url = req.getRequestURI();
        switch (url) {
            case ConstantUtil.ALL_TESTS_URL:
                getAllTests(req, resp);
                break;
            case ConstantUtil.QUERY_TEST_URL:
                getTest(req, resp);
                break;
            case ConstantUtil.ADD_TEST_URL:
                addTest(req, resp);
                break;
            case ConstantUtil.UPDATE_TEST_URL:
                updateTest(req, resp);
                break;
            case ConstantUtil.DELETE_TEST_URL:
                delTest(req, resp);
                break;
        }
    }

    private void getAllTests(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        List<Test> list = testBusiness.getAll();
        CommonModel commonModel = new CommonModel();
        if (list.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(list);
        } else {
            commonModel.setFail();
        }
        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }

    private void getTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        String id = req.getParameter("id");
        System.out.println("id = " + id);
        Test test = testBusiness.getTest(id);
        CommonModel commonModel = new CommonModel();
        if (test != null) {
            commonModel.setSuccess();
            commonModel.setData(test);
        } else {
            commonModel.setFail();
        }
        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }


    private void addTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        String avatar = req.getParameter("avatar");
        System.out.println("avatar = " + avatar);
        String name = req.getParameter("name");
        System.out.println("name = " + name);
        CommonModel commonModel = new CommonModel();
        Test test = new Test();
        test.setAvatar(avatar);
        test.setName(name);
        if (testBusiness.insertTest(test)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }

    private void updateTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        String name = req.getParameter("name");
        System.out.println("name = " + name);
        CommonModel commonModel = new CommonModel();
        String id = req.getParameter("id");
        if (testBusiness.updateTest(id, name)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }


    private void delTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        String id = req.getParameter("id");
        System.out.println("id = " + id);
        CommonModel commonModel = new CommonModel();
        if (testBusiness.deleteDeveloper(id)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        printWriter.println(GsonUtil.bean2Json(commonModel));
        printWriter.flush();
        printWriter.close();
    }
}
