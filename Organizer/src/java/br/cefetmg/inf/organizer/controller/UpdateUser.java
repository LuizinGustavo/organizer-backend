
package br.cefetmg.inf.organizer.controller;

import br.cefetmg.inf.organizer.model.domain.Tag;
import br.cefetmg.inf.organizer.model.domain.User;
import br.cefetmg.inf.organizer.model.service.IKeepTag;
import br.cefetmg.inf.organizer.model.service.IKeepUser;
import br.cefetmg.inf.organizer.model.service.impl.KeepTag;
import br.cefetmg.inf.organizer.model.service.impl.KeepUser;
import br.cefetmg.inf.util.GsonUtil;
import br.cefetmg.inf.util.PasswordCriptography;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUser implements GenericProcess {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String result = "";
        
        String email =null;
        String name = null;
        String password = null;
        
        Map<String,Object> parameterMap = (Map<String,Object>) req.getAttribute("mobile-parameters");
        email = (String) parameterMap.get("email");
        name = (String) parameterMap.get("name");
        password = (String) parameterMap.get("password");
        boolean isHashed = (boolean) parameterMap.get("isHash");
        
        if(!isHashed){
            password = PasswordCriptography.generateMd5(password);
        }
        
        
        User tempUser = new User();
        tempUser.setCodEmail(email);
        tempUser.setUserName(name);
        tempUser.setUserPassword(password);
        tempUser.setCurrentTheme(1);

        IKeepUser keepUser = new KeepUser();
        boolean success = keepUser.updateUser(tempUser);
        
        result = GsonUtil.toJson(success);
        return result;
    }

}
