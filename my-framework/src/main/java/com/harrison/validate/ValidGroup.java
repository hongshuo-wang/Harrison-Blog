package com.harrison.validate;

import javax.validation.groups.Default;

/**
 * @author: Harrison
 * @date: 2023/2/19 23:19
 * @Description: TODO
 */
public interface ValidGroup extends Default {
    interface Crud extends ValidGroup {
        interface Create extends Crud{

        }
        interface Update extends Crud{

        }
        interface Query extends Crud{

        }
        interface Delete extends Crud{

        }
    }
}
